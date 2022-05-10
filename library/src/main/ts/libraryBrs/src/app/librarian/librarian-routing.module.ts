import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibLoginComponent } from './lib-auth/lib-login/lib-login.component';

const routes: Routes = [

  { path: 'auth', loadChildren: () => import('./lib-auth/lib-auth.module').then(m => m.LibAuthModule) },
  { path: 'books', loadChildren: () => import('./lib-book/lib-book.module').then(m => m.LibBookModule) },
  { path: 'user', loadChildren: () => import('./lib-user/lib-user.module').then(m => m.LibUserModule) },

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
