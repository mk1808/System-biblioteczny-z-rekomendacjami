import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from '../shared/shared.module';
import { BookOpinionComponent } from './book-opinion/book-opinion.component';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    BookOpinionComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    SharedModule
  ],
  
})
export class AuthModule { }
