import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibLoginComponent } from './lib-login/lib-login.component';

const routes: Routes = [

  { path: 'login', component: LibLoginComponent },
  { path: '**', component: LibLoginComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LibAuthRoutingModule { }
