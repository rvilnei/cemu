import { Component, OnInit } from '@angular/core';
import { TransportadorasService } from 'src/app/transportadoras/transportadoras.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-transportadoras',
  templateUrl: './transportadoras.component.html',
  styleUrls: ['./transportadoras.component.css']
})
export class TransportadorasComponent implements OnInit {

  transportadoras$:Observable<any[]>;
   
  currentPage:number = 1;
  numPages:number = 0;
  itemsPerPage = 8;

  constructor(
    private transportadorasService: TransportadorasService
  ) { }

  ngOnInit(): void {
    this.transportadoras$ = this.transportadorasService.getTransportadoras();
    // this.transportadoras$.subscribe(
    //   resp => {console.log( "transportadora ** : ", resp ); }
    // );
  }

}
