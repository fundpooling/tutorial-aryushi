import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent {
  user = {
    name: '',
    email: '',
    mobileNumber: '',
    address: '',
  };

  errors: any = {};
  apiUrl = 'http://localhost:9090/api/users';
  constructor(private router: Router) {}
  private http = inject(HttpClient);

  validateForm() {
    this.errors = {};

    if (!this.user.name.trim()) this.errors.name = 'Name is required';
    if (!this.user.email.match(/^\S+@\S+\.\S+$/))
      this.errors.email = 'Invalid email';
    if (!this.user.mobileNumber.match(/^\d{10}$/))
      this.errors.mobileNumber = 'Mobile must be 10 digits';
    if (!this.user.address.trim()) this.errors.address = 'Address is required';

    return Object.keys(this.errors).length === 0;
  }

  addUser() {
    if (this.validateForm()) {
      this.http.post(this.apiUrl, this.user).subscribe({
        next: (response) => {
          console.log('User created successfully:', response);
          alert('User created successfully!');
          this.router.navigate(['/home']);
        },
        error: (error) => {
          console.error('Error creating user:', error);
          alert('Error creating user.');
          this.router.navigate(['/home']);
        },
      });
    }
  }
}
