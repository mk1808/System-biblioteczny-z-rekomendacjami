import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  title="register";
  rulesHeight="rulesHeight";

  inputClass="log-reg";
  iconClass="search"
  text="search"
  btnClass="full"
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="createAccount"
  iconClass2="sign-in-alt";
  goLogin="goLogin";
  mailInput="mail"
  passwordInput="password"
  passwordRepeatInput="passwordRepeat"
  nameInput="name"
  surnameInput="surname"
  phoneInput="phoneNo"
  streetInput="street"
  houseNoInput="houseNo"
  flatNoInput="flatNo"
  postalCodeInput="postalCode"
  cityInput="city"

  
  constructor() { }

  ngOnInit(): void {
  }

}
