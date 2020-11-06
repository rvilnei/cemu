import { Component, OnInit, Input } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'guia-new-modal',
  templateUrl: './new-modal.component.html',
  styleUrls: ['./new-modal.component.css']
})
export class NewModalComponent implements OnInit {
  @Input() movimentacao: any;
  
  constructor() { }

  ngOnInit() {
  }

}
