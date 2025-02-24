import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SurveyService {
  private apiUrl = 'http://localhost:9090/api/survey-responses';

  constructor(private http: HttpClient) {}

  submitResponse(responseData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, responseData);
  }
}
