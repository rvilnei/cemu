import { Observable } from 'rxjs';
import { PendenciasService } from './pendencias.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pendencias',
  templateUrl: './pendencias.component.html',
  styleUrls: ['./pendencias.component.css']
})
export class PendenciasComponent implements OnInit {

  pendencias$: Observable<any[]>;

  constructor( private pendenciasService: PendenciasService  ) { }

  ngOnInit(): void {
    this.pendencias$ = this.pendenciasService.getTiposPendencias();
  }

}
