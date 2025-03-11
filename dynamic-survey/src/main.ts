import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http'; // ✅ Import this
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(), // ✅ Add this
  ],
}).catch((err) => console.error(err));
