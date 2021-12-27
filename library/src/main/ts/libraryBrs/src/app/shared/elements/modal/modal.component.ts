import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
declare let $ : any;

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {
  prolongText="Zatwierdź"
  cancelText="Anuluj"
  btnClass="full orange"
  cancelClass="full gray"

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {

;
  }

}
