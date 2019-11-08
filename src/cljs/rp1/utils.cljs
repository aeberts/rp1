(ns rp1.utils
  (:require
    ;;[re-posh.core :as re-posh]
    ;;[rp1.core :as rp1]
    ;;[rp1.events :as events]
    [rp1.db :as db]
    [datascript.core :as d]
    [cljs.pprint :as pp]))

(defn prn-db []
  "Utility function to print database to the console"
  (pp/pprint (d/datoms (deref db/conn) :eavt)))

; (defn erase-db! []
;   "Completely resets the database to an empty db"
;   (re-posh/dispatch-sync [::events/reset-db db/blank-db]))

(defn erase-db! []
  "Completely resets the database to an empty db"
  (d/reset-conn! db/conn db/blank-db))

(defn reset-db! []
  "Completely resets the database to the value of db/initial-db"
  (d/reset-conn! db/conn db/initial-db))

(comment 
  (prn-db)
  )