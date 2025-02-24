import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-survey',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css'],
})
export class SurveyComponent implements OnInit {
  questions: any[] = [
    {
      id: 1,
      question: 'What is your name?',
      type: 'text',
      required: true,
    },
    {
      id: 2,
      question: 'What is your gender?',
      type: 'multiple-choice',
      required: true,
      options: ['Male', 'Female', 'Other'],
    },
    {
      id: 3,
      question: 'Which country do you live in?',
      type: 'dropdown',
      required: true,
      options: ['India', 'USA', 'UK', 'Canada'],
    },
  ];

  userResponses: { [key: string]: any } = {};

  constructor(private http: HttpClient) {}

  ngOnInit() {
    console.log('Survey Questions:', this.questions);
  }

  submitForm() {
    const responses = Object.keys(this.userResponses).map((key) => ({
      question: this.questions.find((q) => q.id.toString() === key)?.question,
      answer: this.userResponses[key],
    }));

    console.log('Formatted User Responses:', responses);

    this.http
      .post('http://localhost:9090/api/survey/submit', responses)
      .subscribe({
        next: (response) => {
          alert('Survey submitted successfully!');
          console.log('Response from backend:', response);
        },
        error: (error) => {
          alert('Failed to submit survey!');
          console.error('Error:', error);
        },
      });
  }
}
