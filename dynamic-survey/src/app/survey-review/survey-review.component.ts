import { Component, OnInit, inject, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { User } from '../survey/survey.component';

@Component({
  selector: 'app-survey-review',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './survey-review.component.html',
  styleUrls: ['./survey-review.component.css'],
})
export class SurveyReviewComponent implements OnInit {
  questions: any[] = [];
  userResponses: { [key: string]: any } = {};
  users: User[] = [];
  surveySubmitted: boolean = false;
  selectedUser: User = {
    id: 0,
    name: '',
    address: '',
    email: '',
    mobile_number: '',
  };

  constructor(private router: Router) {}
  private http = inject(HttpClient);
  ngOnInit() {
    this.loadUsers();
  }
  loadUsers() {
    this.http.get<any[]>('http://localhost:9090/api/users').subscribe({
      next: (data) => {
        this.users = data.map((user) => ({
          id: user.id,
          address: user.address,
          email: user.email,
          mobile_number: user.mobile_number,
          name: user.name,
        }));

        console.log('Users Loaded:', this.users);
      },
      error: (error) => {
        console.error('Error loading users:', error);
      },
    });
  }

  loadQuestions() {
    const url = `http://localhost:9090/api/survey/${this.selectedUser}`;
    this.http.get<{ responses: any[] }>(url).subscribe({
      next: (data) => {
        this.questions = data.responses || [];
        console.log('Survey Questions Loaded:', this.questions);
        this.surveySubmitted = this.questions.length > 0;
      },
      error: (error) => {
        console.error('Error loading survey questions:', error);
        this.surveySubmitted = false;
      },
    });
  }

  onUserChange(selectedUser: any) {
    this.selectedUser = selectedUser ?? 0;
    console.log('Selected User ID:', this.selectedUser);
    this.loadQuestions();
  }

  goHome() {
    this.router.navigate(['/home']);
  }

  editSurvey() {
    this.router.navigate(['/survey']);
  }

  deleteSurvey() {
    const url = `http://localhost:9090/api/survey/delete/${this.selectedUser}`;
    if (confirm('Are you sure you want to delete this survey?')) {
      this.http.delete(url).subscribe({
        next: (response) => {
          console.log('Survey deleted successfully:', response);
          alert('Survey deleted successfully!');
          this.router.navigate(['/home']);
        },
        error: (error) => {
          alert('Failed to delete survey!');
          console.error('Error deleting survey:', error);
        },
      });
    }
  }
}
