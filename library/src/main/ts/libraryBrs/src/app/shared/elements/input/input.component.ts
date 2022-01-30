import { Component, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: InputComponent
    }
  ]
})
export class InputComponent implements OnInit, ControlValueAccessor {
  @Input() label: any;
  @Input() inputClass: any;
  @Input() type: any;
  @Input() disabled: any;
  disabledClass = '';
  labelTranslated = ""

  value = ""
  onChange = (quantity: any) => { };
  onTouched = () => { };

  constructor(private i18nService: I18nService) { }

  writeValue(value: any): void {
    this.value = value;
  }
  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }
  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }

  ngOnInit(): void {
    this.labelTranslated = this.i18nService.getTranslation(this.label);
    this.type = this.type ? this.type : 'text';
    this.disabledClass = this.disabled ? 'disabled' : ''
  }

  change() {
    this.onChange(this.value)
  }

}
