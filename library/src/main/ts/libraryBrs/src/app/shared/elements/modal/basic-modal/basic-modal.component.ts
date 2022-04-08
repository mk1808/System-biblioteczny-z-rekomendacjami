import { Component, Input, OnChanges, OnInit, SimpleChanges, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-basic-modal',
  templateUrl: './basic-modal.component.html',
  styleUrls: ['./basic-modal.component.scss']
})
export class BasicModalComponent implements OnInit{
  @Input() name:any;
  @Input() modalTitle:any;
  @Input() header:any;
  @Input() onConfirm:any;
  @Input() onCancel:any;
  @Input() internalTemplate?: TemplateRef<any> | null;
  activeInternalTemplate: TemplateRef<any>;
  title = "My modal title"

  constructor() {}

  ngOnInit(): void {
    console.log(this.internalTemplate)
    let n;
    if (this.internalTemplate != null) {
      let template: TemplateRef<any> = this.internalTemplate;
      this.activeInternalTemplate = template;
    }
  }

}
