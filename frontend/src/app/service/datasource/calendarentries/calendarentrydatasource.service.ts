import { Injectable } from '@angular/core';
import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { BehaviorSubject, Observable, catchError, finalize, of } from 'rxjs';
import { CalendarEntry } from '../../../models/calendarentry';
import { CalendarEntryService } from '../../calendarntries/calendarentry.service';

@Injectable({
  providedIn: 'root'
})
export class CalendarEntriesDataSource implements DataSource<CalendarEntry> {

  private calendarEntrySubject = new BehaviorSubject<CalendarEntry[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(private calendarEntryService: CalendarEntryService) { }
  
  connect(collectionViewer: CollectionViewer): Observable<CalendarEntry[]> {
    return this.calendarEntrySubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.calendarEntrySubject.complete();
    this.loadingSubject.complete();
  }

  public loadCalendarEntries(calendarUuid: string) {
    this.loadingSubject.next(true);

    this.calendarEntryService.getCalendarEntries(calendarUuid).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(freezeritems => this.calendarEntrySubject.next(freezeritems));
  }
}
