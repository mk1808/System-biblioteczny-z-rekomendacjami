import { Component, Input, OnInit } from '@angular/core';
declare let $ : any;
@Component({
  selector: 'app-notification-bar',
  templateUrl: './notification-bar.component.html',
  styleUrls: ['./notification-bar.component.scss']
})
export class NotificationBarComponent implements OnInit {
  @Input() title:any;
  @Input() content:any;
  @Input() type:any;
  
  constructor() { }

  ngOnInit(): void {
  }
  close(event: Event){
    let event1:any=event.target;
    debugger;
    $(this)
      .closest('.message')
      .transition('fade')
    ;
    
  }

  
}
