import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lib-login',
  templateUrl: './lib-login.component.html',
  styleUrls: ['./lib-login.component.scss']
})
export class LibLoginComponent implements OnInit {
  title = "loginLib";
  contentClass = "contactHeight";
  contactHeight= "contactHeight";
  mailInput = "mail"
  passwordInput = "password"
  inputClass = "log-reg";
  iconClass = "search"
  text = "search"
  btnClass = "full"
  rightClass = "infoFull"
  leftClass = "info"
  textLogin = "login"
  textRegister = "goToRegister"
  textOpen = "open"


  constructor() { }

  ngOnInit(): void {
  }

 


}
