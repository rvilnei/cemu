import { TestBed } from '@angular/core/testing';

import { TransportadorasService } from './transportadoras.service';

describe('TransportadorasService', () => {
  let service: TransportadorasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransportadorasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
