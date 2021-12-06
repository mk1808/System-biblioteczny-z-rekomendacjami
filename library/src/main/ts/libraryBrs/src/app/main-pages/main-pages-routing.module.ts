import { NgModule } from '@angular/core';
import { MainComponent } from './main/main.component';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { RulesComponent } from './rules/rules.component';


const routes: Routes = [

  { path: 'contact',  component: ContactComponent },
  { path: 'terms',  component: RulesComponent },
  { path: '**',  component: MainComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class MainPagesRoutingModule { }
