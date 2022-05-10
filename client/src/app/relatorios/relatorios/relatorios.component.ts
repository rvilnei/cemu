import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { RelatoriosService } from 'src/app/relatorios/relatorios.service';

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['./relatorios.component.css']
})
export class RelatoriosComponent implements OnInit {
  inscricao: Subscription;
  guiaId: number;

  constructor(
    private route: ActivatedRoute,
    private relatoriosService: RelatoriosService
  ) { }

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (params: any) => {
        this.guiaId = params['id'];
        this.relatoriosService.imprimeGuia(this.guiaId ).subscribe();
      }
    );
  }

}
