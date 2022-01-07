import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibLoginComponent } from './lib-login/lib-login.component';
import { LibAuthRoutingModule } from './lib-auth-routing.module';



@NgModule({
  declarations: [LibLoginComponent],
  imports: [
    CommonModule,
    LibAuthRoutingModule
  ]
})
export class LibAuthModule { }
