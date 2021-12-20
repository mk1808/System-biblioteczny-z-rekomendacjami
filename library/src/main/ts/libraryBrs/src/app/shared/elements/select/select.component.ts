import { Component, ElementRef, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {

  constructor(private element: ElementRef) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
   
    let elements = this.element.nativeElement.querySelectorAll('.ui.dropdown');
 }

}
