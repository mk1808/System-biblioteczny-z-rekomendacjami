import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {
  title = 'addBook';
  rulesHeight = "rulesHeight";
  mailInput="mail";
  passwordInput="password"
  inputClass="log-reg";
  iconClass="search"
  text="search"
  btnClass="full"
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="goToRegister"
  textOpen="open"
  constructor() { }

  ngOnInit(): void {
  }

}
