import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { HeaderButton } from '../types';
import { UsersService } from './rest/users.service';

@Injectable({
  providedIn: 'root'
})

export class HeaderService {
  public navButtons: BehaviorSubject<[]> = new BehaviorSubject([]);
  constructor(private router: Router, private userService: UsersService) { 
    setTimeout((() => userService.logged.subscribe(this.setButtonsList.bind(this))), 1000);
  }


  getButtonsLoggedUser():any{
    let buttons:HeaderButton[]=[];
    buttons.push(this.createButton('/books/search', 'booksSearch'));
    buttons.push(this.createButton('/recommendation/all', 'recommendation'));
    buttons.push(this.createButton('/home/terms', 'borrowingRules'));
    buttons.push(this.createButton('/books/myLists', 'myBooks'));
    buttons.push(this.createButton('/user/myAccount', 'myAccount'));
    buttons.push(this.createButton('/home/contact', 'contact'));
    return buttons;
  }

  getButtonsNotLoggedUser():any{
    let buttons:HeaderButton[]=[];
    buttons.push(this.createButton('/books/search', 'booksSearch'));
    buttons.push(this.createButton('/home/terms', 'borrowingRules'));
    buttons.push(this.createButton('/auth/login', 'login'));
    buttons.push(this.createButton('/auth/register', 'register'));
    buttons.push(this.createButton('/home/contact', 'contact'));
    return buttons;
  }

  getButtonsLoggedLibrarian():any{
    let buttons:HeaderButton[]=[];
    buttons.push(this.createButton('/librarian/books/add', 'booksAdd'));
    buttons.push(this.createButton('/librarian/books/search', 'booksSearch'));
    buttons.push(this.createButton('/home/terms', 'borrowingRules'));
    buttons.push(this.createButton('/librarian/user/myAccount', 'myAccount'));
    buttons.push(this.createButton('/home/contact', 'contact'));
    return buttons;
    
  }

  getButtonsNotLoggedLibrarian():any{
    let buttons:HeaderButton[]=[];
    buttons.push(this.createButton('/books/search', 'booksSearch'));
    buttons.push(this.createButton('/home/terms', 'borrowingRules'));
    buttons.push(this.createButton('/librarian/auth/login', 'login'));
    buttons.push(this.createButton('/home/contact', 'contact'));
    return buttons;
    
  }

  createButton(routerLink:string,name:string){
    let button:HeaderButton={};
    button.isActive=true;
    button.routerLink = routerLink;
    button.name = name;
    return button;
  }

  setButtonsList(isLogged: Boolean) {
    debugger;
    let url: string = this.router.url;
    let isLibrarian = url.includes("librarian");

    if (isLibrarian) {
      if (isLogged) {
        this.navButtons.next(this.getButtonsLoggedLibrarian());
      } else {
        this.navButtons.next(this.getButtonsNotLoggedLibrarian());
      }
    }
    else {
      if (isLogged) {
        this.navButtons.next(this.getButtonsLoggedUser());
      } else {
        this.navButtons.next(this.getButtonsNotLoggedUser());
      }
    }

  }
}
