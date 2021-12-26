import { Component, Inject, OnInit } from '@angular/core';
import { NgbAccordion } from '@ng-bootstrap/ng-bootstrap';
import { I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import i18next from 'i18next';
@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {
  title="myBooks";
  rulesHeight = "rulesHeight";
  tabHeaders=this.getHeaders();
  a = (i18next||"register");
  constructor( @Inject(I18NEXT_SERVICE) private i18NextService: ITranslationService) { }

  ngOnInit(): void {
    
    this.a = this.getTranslation("register")
    debugger;
  }

  getTranslation(key:String){
    return this.i18NextService.getResource("pl", "translation", key, "");
  }

  getHeaders() {
    return {
      borrowed: this.getTranslation("booklist.borrowed"), 
      borrowedInPast: this.getTranslation("booklist.borrowedInPast"),
      reserved: this.getTranslation("booklist.reserved"), 
      toReadList: this.getTranslation("booklist.toReadList"), 
      favList: this.getTranslation("booklist.favList"), 
      rated: this.getTranslation("booklist.rated")
    }
  }

}
