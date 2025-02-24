import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SurveyComponent } from './survey/survey.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, SurveyComponent],
  template: `<app-survey></app-survey>`,
})
export class AppComponent {
  title = 'dynamic-survey';
}
