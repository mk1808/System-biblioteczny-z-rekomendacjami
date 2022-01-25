import { Component, Input, OnInit } from '@angular/core';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {
  @Input() label:any;
  @Input() inputClass:any;
  @Input() type:any;
  @Input() disabled:any;
  disabledClass='';
  labelTranslated =""
  
  constructor(private i18nService: I18nService) { }

  ngOnInit(): void {
    this.labelTranslated = this.i18nService.getTranslation(this.label);
    this.type=this.type?this.type:'text';
    this.disabledClass = this.disabled?'disabled':''
  }

}
