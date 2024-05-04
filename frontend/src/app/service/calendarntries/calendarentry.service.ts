import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, take } from 'rxjs';
import { Calendar } from '../../models/calendar';
import { CalendarEntry } from '../../models/calendarentry';

@Injectable({
  providedIn: 'root'
})
export class CalendarEntryService {
  private path = '/api/calendars';
  private pathSub = '/entries';

  constructor(private http: HttpClient) {}
  
  getCalendarEntries(calendarUuid: string): Observable<any> {
    return this.http.get<CalendarEntry[]>(this.path + "/" + calendarUuid + this.pathSub);
  }

  getCalendarEntry(calendarUuid: string, uuid: string): Observable<any> {
    return this.http.get<CalendarEntry>(this.path + "/" + calendarUuid + this.pathSub + "/" + uuid)
  }

  deleteCalendarEntry(calendarUuid: string, uuid: string): Observable<any> {
    return this.http.delete<CalendarEntry>(this.path + "/" + calendarUuid + this.pathSub + "/" + uuid)
  }

  updateCalendarEntry(calendarUuid: string, uuid: string, calendarEntry: CalendarEntry): Observable<any> {
    return this.http.put<CalendarEntry>(this.path + "/" + calendarUuid + this.pathSub + "/" + uuid, calendarEntry)
  }

  createCalendarEntry(calendarUuid: string, calendarEntry: CalendarEntry): Observable<any> {
    return this.http.post<CalendarEntry>(this.path + "/" + calendarUuid + this.pathSub, calendarEntry)
  }
  
}
