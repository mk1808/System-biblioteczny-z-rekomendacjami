import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
  display=false;

  @Output('update')
  change = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
    setTimeout(()=>{
      
      this.change.emit("close");
    }, 5000);
  }
  
  close(event: Event){
      this.change.emit("close");
  }

}
