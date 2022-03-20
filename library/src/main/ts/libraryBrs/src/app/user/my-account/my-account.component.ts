import { Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  iconClass="search";
  text="search";
  btnClass="full";
  rightClass="info";
  leftClass="info";
  textLogin="login";
  textRegister="goToRegister";
  
  mailInput="mail";
  passwordInput="password";
  passwordRepeatInput="passwordRepeat";
  nameInput="name";
  surnameInput="surname";
  phoneInput="phoneNo";
  streetInput="street";
  houseNoInput="houseNo";
  flatNoInput="flatNo";
  postalCodeInput="postalCode";
  cityInput="city";
  mailDisabled=true;
  editTextLogin="edit";
  nameForm="name";
  surnameForm="surname";
  editForm:FormGroup= this.initForm();

  constructor(private element: ElementRef){}
  ngOnDestroy(): void {
    this.initForm();
  }

  ngOnInit(): void { 
    debugger;
     let elements:any = document.getElementsByClassName('dmenu');
     //$(elements[0]).css('display', 'none')
  }

  initForm(){
    return new FormGroup({
      name: new FormControl('Jan'),
      surname: new FormControl('Nowak'),
      phoneNo:new FormControl('655 625 258'),
      mail:new FormControl('jNowak@test.com.pl'),
      street:new FormControl('Nowa'),
      houseNo:new FormControl('2/4'),
      flatNo:new FormControl('16'),
      postalCode:new FormControl('61-234'),
      city:new FormControl('Poznań'),
    });
  }

  onSubmit(form:any){



  }

 

}