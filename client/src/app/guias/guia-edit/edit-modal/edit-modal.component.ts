import { Component, OnInit, Input, Output } from '@angular/core';
import { Guia } from 'src/app/guias/guia';

@Component({
  selector: 'edit-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditModalComponent implements OnInit {
  @Input() guiaImput: Guia;
  guia: Guia;
  constructor() { }

  ngOnInit() {
    this.guia = this.guiaImput;
  }

}
