import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from 'src/app/shared/elements/modal/modal.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title="login";
  contactHeight="contactHeight";
  mailInput="mail"
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

  
  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  openModal=()=>{
    console.log("abc")
    debugger;
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.name = 'World';
  }


    


}
