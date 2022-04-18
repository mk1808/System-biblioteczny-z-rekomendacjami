import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibLoginComponent } from './lib-login/lib-login.component';
import { LibAuthRoutingModule } from './lib-auth-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [LibLoginComponent],
  imports: [
    CommonModule,
    LibAuthRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class LibAuthModule { }
