import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibMyAccountComponent } from './lib-my-account/lib-my-account.component';

const routes: Routes = [
    { path: 'myAccount', component: LibMyAccountComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LibUserRoutingModule { }
