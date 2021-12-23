import { AfterViewInit, Component, ElementRef, Input, OnInit } from '@angular/core';
//import * as $ from 'jquery';
declare let $ : any;
@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit, AfterViewInit {
  @Input() label:any;
  constructor(private element: ElementRef) { }

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
    $('.search.dropdown').dropdown();
 }

}
