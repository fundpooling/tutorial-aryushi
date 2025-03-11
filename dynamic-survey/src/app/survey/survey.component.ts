import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

export interface User {
  id: number;
  address: string;
  email: string;
  mobile_number: string;
  name: string;
}
@Component({
  selector: 'app-survey',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css'],
})
export class SurveyComponent implements OnInit {
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

  private http = inject(HttpClient);
  constructor(private router: Router) {}

  ngOnInit() {
    this.loadQuestions();
    this.loadUsers();
  }

  loadQuestions() {
    this.http.get<any[]>('/assets/survey-question.json').subscribe({
      next: (data) => {
        this.questions = data;
        console.log('Survey Questions Loaded:', this.questions);
      },
      error: (error) => {
        console.error('Error loading survey questions:', error);
      },
    });
  }
  onUserChange(selectedUser: any) {
    this.selectedUser = selectedUser ?? 0;
    console.log('Selected User ID:', this.selectedUser);
    this.checkIfSurveySubmitted();
  }
  checkIfSurveySubmitted() {
    if (!this.selectedUser) return;

    const url = `http://localhost:9090/api/survey/${this.selectedUser}`;
    this.http.get<{ status: string }>(url).subscribe({
      next: (response) => {
        console.log('Survey Submission Status:', response);
        if (response !== null) {
          alert('User has already submitted the survey.');
          this.surveySubmitted = true;
        } else {
          this.surveySubmitted = false;
        }
      },
      error: (error) => {
        console.error('Error checking survey submission:', error);
        this.surveySubmitted = false;
      },
    });
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

  submitForm() {
    if (!this.selectedUser) {
      alert('Please select a user.');
      return;
    }
    if (this.surveySubmitted) {
      alert('User has already submitted the survey.');
      return;
    }

    const unansweredQuestions = this.questions.filter((question) => {
      return !this.userResponses[question.id];
    });

    if (unansweredQuestions.length > 0) {
      alert('Please answer all the questions before submitting.');
      return;
    }

    const responses = Object.keys(this.userResponses).map((key) => {
      const question = this.questions.find((q) => q.id.toString() === key);
      let answer = this.userResponses[key];

      if (Array.isArray(answer)) {
        answer = answer.join(', ');
      }

      return {
        question: question?.question,
        answer: answer,
      };
    });

    // const payload = {
    //   userId: this.selectedUser,
    //   responses,
    // };
    console.log(responses);
    console.log(this.selectedUser);
    const url = `http://localhost:9090/api/survey/submit/${this.selectedUser}`;

    this.http.post(url, responses).subscribe({
      next: (response) => {
        alert('Survey submitted successfully!');
        console.log('Response from backend:', response);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        alert('Failed to submit survey!');
        console.error('Error:', error);
      },
    });
  }
  backToHome() {
    console.log('Redirect to home page');
    this.router.navigate(['/home']);
  }

  updateCheckboxSelection(event: Event, questionId: number, option: string) {
    const checked = (event.target as HTMLInputElement).checked;

    if (!this.userResponses[questionId]) {
      this.userResponses[questionId] = [];
    }

    if (checked) {
      this.userResponses[questionId].push(option);
    } else {
      this.userResponses[questionId] = this.userResponses[questionId].filter(
        (item: string) => item !== option
      );
    }
  }
}
