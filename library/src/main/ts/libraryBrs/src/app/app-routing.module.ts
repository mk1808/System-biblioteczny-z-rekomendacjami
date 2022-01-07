import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'  },
  { path: 'home', loadChildren: () => import('./main-pages/main-pages.module').then(m => m.MainPagesModule) },
  { path: 'books', loadChildren: () => import('./book/book.module').then(m => m.BookModule) },
  { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) },
  { path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule) },
  { path: 'recommendation', loadChildren: () => import('./recommendation/recommendation.module').then(m => m.RecommendationModule) },
  { path: 'librarian', loadChildren: () => import('./librarian/librarian.module').then(m => m.LibrarianModule) },
  { path: '**', redirectTo: '/home',}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
