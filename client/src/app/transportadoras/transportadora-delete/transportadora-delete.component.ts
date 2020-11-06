import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-transportadora-delete',
  templateUrl: './transportadora-delete.component.html',
  styleUrls: ['./transportadora-delete.component.css']
})
export class TransportadoraDeleteComponent implements OnInit {
  @Input() transportadora: any;
  transportadoras$:Observable<any[]>;
  @Output() atualizaLista: EventEmitter<Observable<any[]> > = new EventEmitter();

  constructor(
    private transportadorasService: TransportadorasService,
    private router: Router
  ) { }

  delete(transportadora): void {
    this.transportadorasService.delete(transportadora)
      .subscribe( retorno => {
        this.transportadoras$ = this.transportadorasService.getTransportadoras();
        this.atualizaLista.emit(this.transportadoras$);
        console.log( "delete *****:, this.transportadoras$ " );
      } )
  }

  // this.transportadoras$.subscribe(
  //   resp => {console.log( "transportadora ** : ", resp ); }
  // );

  ngOnInit(): void {
  }

}
