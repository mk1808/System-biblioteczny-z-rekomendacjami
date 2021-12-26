import { Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
declare let $ : any;
@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit, OnDestroy {
  title="myAccount";
  rulesHeight="rulesHeight";

  inputClass="log-reg";
  iconClass="search"
  text="search"
  btnClass="full"
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="goToRegister"
  
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
  mailDisabled=true;

  constructor(private element: ElementRef){}
  ngOnDestroy(): void {
    
  }

  ngOnInit(): void { 
    debugger;
     let elements:any = document.getElementsByClassName('dmenu');
     //$(elements[0]).css('display', 'none')
  }

 

}