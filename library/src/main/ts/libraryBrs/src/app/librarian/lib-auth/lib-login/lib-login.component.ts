import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from 'src/app/core/services/rest/api/api';
import { UsersService } from 'src/app/core/services/rest/users.service';

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
  submitBtnType="submit";
  mail="mail";
  pass="password";
  loginForm:FormGroup= new FormGroup({});

  constructor(private usersService:UsersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.loginForm = new FormGroup({
      mail: new FormControl(''),
      password: new FormControl('')
    });
  }
 
  onSubmit(form:any){
    let login:Login = this.loginForm.value;
    this.usersService.login(login).subscribe(resp=>{
      
      this.router.navigate(['/user/myAccount']);
      console.log(resp)
    })


  }


}
