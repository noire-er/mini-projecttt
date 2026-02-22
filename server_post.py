#!/usr/bin/env python3
import os
from http.server import SimpleHTTPRequestHandler, HTTPServer
from urllib.parse import parse_qs
import json
import hashlib

PORT = 8000
WEBROOT = os.path.join(os.path.dirname(__file__), 'src', 'resources', 'static')

class Handler(SimpleHTTPRequestHandler):
    def do_POST(self):
        if self.path != '/signup':
            self.send_error(404, 'Not Found')
            return
        length = int(self.headers.get('Content-Length', 0))
        body = self.rfile.read(length).decode('utf-8')
        data = parse_qs(body)

        # Extract fields
        name = data.get('name', [''])[0].strip()
        surname = data.get('surname', [''])[0].strip()
        email = data.get('email', [''])[0].strip()
        address = data.get('address', [''])[0].strip()
        password = data.get('password', [''])[0]
        confirm = data.get('confirm_password', [''])[0]

        errors = []

        # Basic validations matching client-side rules
        if len(name) < 2:
            errors.append('First name must be at least 2 letters')
        if any(ch.isdigit() for ch in name):
            errors.append('First name cannot contain numbers')

        if len(surname) < 2:
            errors.append('Last name must be at least 2 letters')
        if any(ch.isdigit() for ch in surname):
            errors.append('Last name cannot contain numbers')

        if not email or '@' not in email or '.' not in email or ' ' in email:
            errors.append('Invalid email address')

        if not address:
            errors.append('Address is required')

        if not password:
            errors.append('Password cannot be empty')
        if len(password) < 8:
            errors.append('Password must be at least 8 characters long')
        if not any(c.isupper() for c in password):
            errors.append('Password must contain at least one uppercase letter')
        if not any(c.islower() for c in password):
            errors.append('Password must contain at least one lowercase letter')
        if password != confirm:
            errors.append('Password and confirmation do not match')

        if errors:
            # Respond with an error page listing validation messages
            self.send_response(400)
            self.send_header('Content-Type', 'text/html; charset=utf-8')
            self.end_headers()
            items = ''.join(f'<li>{e}</li>' for e in errors)
            response = f"""
<html><body>
<h2>Signup failed</h2>
<p>The following errors were found:</p>
<ul>{items}</ul>
<p><a href="/signup.html">Back to signup</a></p>
</body></html>
"""
            self.wfile.write(response.encode('utf-8'))
            return

        # Passed validation — store signup (hash password)
        hashed = hashlib.sha256(password.encode('utf-8')).hexdigest()
        record = {
            'name': name,
            'surname': surname,
            'email': email,
            'address': address,
            'password_hash': hashed
        }

        db_path = os.path.join(os.path.dirname(__file__), 'signups.json')
        try:
            if os.path.exists(db_path):
                with open(db_path, 'r', encoding='utf-8') as f:
                    data_list = json.load(f)
            else:
                data_list = []
        except Exception:
            data_list = []

        data_list.append(record)

        with open(db_path, 'w', encoding='utf-8') as f:
            json.dump(data_list, f, indent=2)

        # Success page
        self.send_response(200)
        self.send_header('Content-Type', 'text/html; charset=utf-8')
        self.end_headers()
        response = f"""
<html><body>
<h2>Signup successful</h2>
<p>Thank you, {name} {surname}. Your account has been created.</p>
<p><a href="/signup.html">Back</a></p>
</body></html>
"""
        self.wfile.write(response.encode('utf-8'))

    # Let GET behave as normal file serving


if __name__ == '__main__':
    os.chdir(WEBROOT)
    server = HTTPServer(('0.0.0.0', PORT), Handler)
    print(f"Serving {WEBROOT} on http://0.0.0.0:{PORT}")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print('\nShutting down')
        server.shutdown()
