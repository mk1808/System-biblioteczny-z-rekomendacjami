import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from '../shared/shared.module';
import { BookOpinionComponent } from './book-opinion/book-opinion.component';
import { NgbModule, NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { SurveyComponent } from './survey/survey.component';


@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    BookOpinionComponent,
    SurveyComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    SharedModule,
    NgbModule,
    NgbRatingModule
    
  ],
  
})
export class AuthModule { }
