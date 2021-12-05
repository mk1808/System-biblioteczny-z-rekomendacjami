import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'home', redirectTo: '/a', pathMatch: 'full'  },
  { path: 'a', 

  loadChildren: () => import('./main-pages/main-pages.module').then(m => m.MainPagesModule)
},
  { path: '**', redirectTo: '/a',}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
