import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibLoginComponent } from './lib-auth/lib-login/lib-login.component';

const routes: Routes = [

  { path: 'auth', loadChildren: () => import('./lib-auth/lib-auth.module').then(m => m.LibAuthModule) },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LibrarianRoutingModule { }
