import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'  },
  { path: 'home', loadChildren: () => import('./main-pages/main-pages.module').then(m => m.MainPagesModule) },
  { path: 'books', loadChildren: () => import('./book/book.module').then(m => m.BookModule) },
  { path: '**', redirectTo: '/home',}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
