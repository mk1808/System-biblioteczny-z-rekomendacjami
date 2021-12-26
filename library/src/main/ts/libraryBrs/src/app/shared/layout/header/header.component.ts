import { AfterViewInit, Component, OnInit } from '@angular/core';
declare let $ : any;
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit(): void {
  }
  ngAfterViewInit() {
   
    //let elements = this.element.nativeElement.querySelectorAll('.ui.dropdown');
//    setTimeout(this.loadJQuery);
    this.loadJQuery();
 }

 
 loadJQuery(){
   /*debugger
   let select:any = $('.search.dropdown')[0];
    select.dropdown();
    debugger*/
    $('.menu.dmenu').dropdown();
 }
}
