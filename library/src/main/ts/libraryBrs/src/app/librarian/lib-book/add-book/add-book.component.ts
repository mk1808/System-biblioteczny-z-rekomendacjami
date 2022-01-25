import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {
  title = 'addBook';
  rulesHeight = "rulesHeight";
  isbnInput="bookAdd.isbn";
  titleInput="bookAdd.title";
  authorInput="bookAdd.author";
  btnClassSmall="small"
  btnClass="full"
  mailInput="mail";
  addBnt=""
  iconClass="add";
  passwordInput="password"
  inputClass="log-reg";
 // iconClass="search"
  text="search"
  label="label"
  label1="label1"
  
  
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="goToRegister"
  textOpen="open"


  @Input() name:any;
  @Input() modalTitle:any;
  @Input() onConfirm:any;
  @Input() onCancel:any;
  
  prolongText="Zatwierdź"
  cancelText="Anuluj"
  confirmBtnClass="full orange"
  cancelClass="full gray"
  constructor() { }

  ngOnInit(): void {
  }

}
