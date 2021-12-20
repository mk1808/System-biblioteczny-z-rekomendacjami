import { Component, ElementRef, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {
  @Input() label:any;
  constructor(private element: ElementRef) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
   
    let elements = this.element.nativeElement.querySelectorAll('.ui.dropdown');
 }

}
