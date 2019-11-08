(ns rp1.events
  (:require
   [re-frame.core :as re-frame]
   [re-posh.core :as re-posh]
   [rp1.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-posh/reg-event-ds
 ::initialize-db
 (fn [_ _]
   db/initial-db))

;; This doesn't appear to work... not sure why
(re-posh/reg-event-ds
  ::reset-db
  (fn [_ [_ new-ds]]
    new-ds))

(re-posh/reg-event-ds
 ::set-task-status
 (fn [_ [_ id status]]
   [[:db/add id :task/done? status]]))

(re-posh/reg-event-ds
 ::set-todo-form-title
 (fn [_ [_ id value]]
   [[:db/add id :create-todo-form/title value]]))

(re-posh/reg-event-ds
 ::create-todo
 (fn [_ [_ id value]]
   [[:db/add id :create-todo-form/title ""]
    {:db/id       -1
     :app/type    :type/task
     :task/title  value
     :task/done?  false}]))

;; Figure out how to update an item in a datascript database
;;
(re-posh/reg-event-ds
  ::set-active-panel
  (fn [_ [id value]]
    [[:db/add id :active-panel/name value]]))


; (re-frame/reg-event-db
;  ::initialize-db
;  (fn-traced [_ _]
;    db/default-db))
;
; (re-frame/reg-event-db
;  ::set-active-panel
;  (fn-traced [db [_ active-panel]]
;    (assoc db :active-panel active-panel)))
