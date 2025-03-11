import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { SurveyComponent } from './survey/survey.component';
import { SurveyReviewComponent } from './survey-review/survey-review.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'add-user', component: UserComponent },
  { path: 'survey', component: SurveyComponent },
  { path: 'survey-review', component: SurveyReviewComponent },
  { path: '**', redirectTo: 'home' }, 
];
