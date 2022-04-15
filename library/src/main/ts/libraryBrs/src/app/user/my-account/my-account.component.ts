import { Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UsersService } from 'src/app/core/services/rest/users.service';
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
  editForm:FormGroup= this.init();

  constructor(private element: ElementRef, private userService: UsersService){}
  ngOnDestroy(): void {
    this.initForm();
  }

  ngOnInit(): void { 
   // debugger;
     let elements:any = document.getElementsByClassName('dmenu');
     this.initForm();
     //$(elements[0]).css('display', 'none')
  }

  initForm(){
    this.userService.whoAmI().subscribe(userPrincipal=>{
    this.userService.getById(userPrincipal.principal.id).subscribe(user=>{
      console.log(user);
      let meUser = user.content;
      let address = meUser.address;
      
      let form = {
        name: new FormControl(meUser.name),
        surname: new FormControl(meUser.username),
        phoneNo:new FormControl(meUser.phoneNo),
        mail:new FormControl(meUser.mail),
        street:new FormControl(address.street),
        houseNo:new FormControl(address.houseNo),
        flatNo:new FormControl(address.flatNo),
        postalCode:new FormControl(address.postcode),
        city:new FormControl(address.city),
      }
      this.editForm = new FormGroup(form)
    })
    })
    
  }

  onSubmit(form:any){
console.log(form)


  }

  init(){
    return new FormGroup({
      name: new FormControl(''),
      surname: new FormControl(''),
      phoneNo:new FormControl(''),
      mail:new FormControl(''),
      street:new FormControl(''),
      houseNo:new FormControl(''),
      flatNo:new FormControl(''),
      postalCode:new FormControl(''),
      city:new FormControl(''),
    });
  }

 

}