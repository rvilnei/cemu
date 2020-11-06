import { TestBed } from '@angular/core/testing';

import { MovimentacoesService } from './movimentacoes.service';

describe('MovimentacoesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MovimentacoesService = TestBed.get(MovimentacoesService);
    expect(service).toBeTruthy();
  });
});
